pipeline {
    agent any
    // 环境变量
    environment {
    	// docker私有仓库凭证
        dockerhub_credentials = credentials('ali-dockerhub')
        // 镜像版本
        image_tag = sh(returnStdout: true,script: 'echo `date +"%Y%m%d%H%M"_``git describe --tags --always`').trim()
    }
    // 入参定义
    parameters {
        string(name: 'project_name', defaultValue: 'mldong-admin', description: '项目名称')
        string(name: 'deploy_type', defaultValue: 'deploy', description: '发布类型')
        string(name: 'git_url', defaultValue: 'git@gitee.com:mldong/mldong.git', description: '仓库地址')
        string(name: 'branch_name', defaultValue: 'master', description: 'git分支')
        string(name: 'profiles', defaultValue: 'test', description: '环境')
        string(name: 'registry_url', defaultValue: 'registry-vpc.cn-zhangjiakou.aliyuncs.com', description: '镜像仓库地址')
        string(name: 'registry_ns', defaultValue: 'mldong/java', description: '镜像命名空间')
        string(name: 'hostname', defaultValue: 'c.mldong.com', description: '绑定的域名')	
        string(name: 'k8sCredentialsId', defaultValue: 'ali-k8s-config', description: 'k8s集群配置id')
        string(name: 'k8sServerUrl', defaultValue: 'https://172.26.22.121:6443', description: 'k8s集群服务地址')
    }
    stages{
        stage('检出代码') {
        	steps{
                // 检出代码
            	checkout([$class: 'GitSCM', branches: [[name: "*/${params.branch_name}"]], 
            	doGenerateSubmoduleConfigurations: false, 
            	extensions: [], 
            	submoduleCfg: [], 
                userRemoteConfigs: [[
                    credentialsId: 'mldong-gitbash', 
                	url: "${params.git_url}"]]])
            }
        }
        stage("编译打包"){
        	agent { 
                docker {
                	image 'maven:3-alpine'
                    args "-v /root/.m2:/root/.m2 -v /root/${params.project_name}-config:/root/${params.project_name}-config"
                }
            }
            steps{
                // 编译打包
               	sh "mvn -B -DskipTests clean package"
               	// 删除当前目录下的config
               	sh "rm -rf config"
               	// 复制配置文件到当前工作空间
               	sh "cp -rf /root/${params.project_name}-config config"
               	sh "pwd"
            }
        }
        stage('构建镜像及推送到docker仓库') {
        	steps {
        		sh "pwd"
        	    // 将前一步的配置文件复制到当前目录下
        		sh "cp -rf ${env.WORKSPACE}@2/config config"
        		// 将前一步生成的jar包复制到当前工作空间下
        		sh "cp -rf ${env.WORKSPACE}@2/${params.project_name}/target/${params.project_name}.jar app.jar"
               	// 登录镜像仓库
               	sh "docker login -u ${dockerhub_credentials_USR} -p ${dockerhub_credentials_PSW} ${params.registry_url}"
               	// 构建镜像
               	sh "docker build -t ${params.registry_url}/${params.registry_ns}/${params.project_name}:${image_tag} ."
               	// 推送镜像到私服
               	sh "docker push ${params.registry_url}/${params.registry_ns}/${params.project_name}:${image_tag}"
               	// 删除当前目录下的config
               	sh "rm -rf config"
               	// 删除当前目录下的app.jar
               	sh "rm -rf app.jar"
        	}
        }
        stage('生成k8s发布模板') {
        	steps {
        		// 生成k8s发布模板
                sh "sed -e 's#{{APP_NAME}}#${params.project_name}#g;s#{{NAMESPACE}}#${params.project_name}-${params.profiles}#g;s#{{PROFILES}}#${params.profiles}#g;s#{{IMAGE_URL}}#${params.registry_url}/${params.registry_ns}/${params.project_name}#g;s#{{IMAGE_TAG}}#${image_tag}#g;s#{{HOST}}#${params.hostname}#g' k8s.tpl > k8s.yaml"
                // 暂存文件
                stash name: "k8s.yaml", includes: "k8s.yaml"
                // 查看文件
                sh "cat k8s.yaml"
        	}
        }
        stage("kubectl apply") {
            agent {
                docker {
                    image 'lwolf/helm-kubectl-docker'
                }
            }
            steps {
                withKubeConfig([credentialsId: "${params.k8sCredentialsId}",serverUrl: "${params.k8sServerUrl}"]) {
                    // 取出文件
                	unstash("k8s.yaml")
					// 发布到k8s集群
                    sh 'kubectl apply -f k8s.yaml'
                }
             }
         }
   }
}