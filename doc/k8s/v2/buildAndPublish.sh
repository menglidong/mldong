#!/bin/bash
source_dir=/java_projects/source/front
project_name=mldong-vue
nfs_project_dir=/mnt/
profile=test
k8s_yaml=/java_projects/$project_name/k8s-$profile.yaml
registry_url=https://registry.npm.taobao.org
if [ -d "$source_dir" ]; then
  echo "源码存放根目录${source_dir}已存在"
else
  echo "源码存放根目录不存在，创建${source_dir}"
  cp -p $source_dir
fi

if [ -d "$source_dir/$project_name" ]; then
  echo "源码已存在，git pull"
  cd $source_dir/$project_name
  git pull
else
  echo "源码不存在，git clone"
  git clone $git_url $source_dir/$project_name
fi
cd $source_dir/$project_name
git pull
git_tag=$(git describe --tags --always)
echo "当前版本：$git_tag"
npm --registry=${registry_url} install --unsafe-perm
npm run build:$profile

if [ $? -ne 0 ]; then
  echo "打包失败"
else
  # 移出index.html,留最后复制
  mv dist/index.html ./
  # 创建目录
  mkdir -p $nfs_project_dir/$project_name
  # 复制文件
  cp -r dist/* $nfs_project_dir/$project_name
  # 复制index.html
  cp index.html $nfs_project_dir/$project_name
  # 还原index.html
  mv index.html dist/index.html
  kubectl apply -f $k8s_yaml
fi


