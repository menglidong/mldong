#!/bin/bash
# 源码存放根目录
source_dir=/java_projects/source/back
# 父工程目录
parent_dir=$source_dir/mldong
# 需要打包的项目名称
project_name=mldong-admin
# 项目部署目录
project_dir=/java_projects/$project_name
# git仓库地址(使用ssh方式的，需要去配置部署公钥)
git_url=git@gitee.com:mldong/mldong.git
registry_url=registry-vpc.cn-zhangjiakou.aliyuncs.com
registry_ns=mldong/java
registry_username=registry_username
registry_password=password
image_url=$registry_url/$registry_ns/$project_name
host=c.mldong.com
# 环境定义
profiles=test
if [ -d "$source_dir" ]; then
  echo "源码存放根目录${source_dir}已存在"
else
  echo "源码存放根目录不存在，创建${source_dir}"
  cp -p $source_dir
fi

if [ -d "$parent_dir" ]; then
  echo "源码已存在，git pull"
  cd $parent_dir
  git pull
else
  echo "源码不存在，git clone"
  git clone $git_url $parent_dir
fi
git_version=$(git rev-parse HEAD)
echo "当前版本号:${git_version}"
image_tag=`date +"%Y%m%d%H%M"_``git describe --tags --always`
cd $parent_dir
mvn clean package -B
# 这里需要判断打包是否成功
if [ $? -ne 0 ]; then
  echo "打包失败"
else
  # 复制jar包
  cp -r -f $parent_dir/$project_name/target/$project_name.jar $project_dir/app.jar
  # 进入项目目录
  cd $project_dir
  # 构建镜像
  docker build -t $registry_url/$registry_ns/$project_name:$image_tag .
  # 登录私服
  docker login -u $registry_username -p ${registry_password} $registry_url
  # 退送到私服
  docker push $registry_url/$registry_ns/$project_name:$image_tag
  sed -e "s#{{APP_NAME}}#$project_name#g;s#{{NAMESPACE}}#$project_name-$PROFILES#g;s#{{PROFILES}}#$profiles#g;s#{{IMAGE_URL}}#$image_url#g;s#{{IMAGE_TAG}}#$image_tag#g;s#{{HOST}}#$host#g" k8s.tpl > k8s.yaml
  if [ $? -ne 0 ]; then
      echo "构建镜像推送到私服失败"
  else
    cat k8s.yaml 
    kubectl apply -f k8s.yaml 
  fi
fi

