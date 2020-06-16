#!/bin/bash
# 源码存放根目录
source_dir=/java_projects/source
# 父工程目录
parent_dir=$source_dir/mldong
# 需要打包的项目名称
project_name=mldong-admin
# 项目部署目录
project_dir=/java_projects/$project_name
# 备份目录
backup_dir=/backup/$project_name
# git仓库地址(使用ssh方式的，需要去配置部署公钥)
git_url=git@gitee.com:mldong/mldong.git

if [ -f "$project_dir/$project_name.jar" ]; then
  echo "备份旧包"
  mkdir -p $backup_dir
  cp $project_dir/$project_name.jar $backup_dir/`date +"%Y%m%d%H%M%S"`.jar
fi

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
# 进入项目源码目录
cd $parent_dir
# 打包
mvn clean package -B
# 这里需要判断打包是否成功
if [ $? -ne 0 ]; then
  echo "打包失败"
else
  # 复制新包到部署目录
  cp $parent_dir/$project_name/target/$project_name.jar $project_dir/$project_name.jar
  # 进入部署项目
  cd $project_dir
  # 重启服务
  bash $project_name.sh
fi