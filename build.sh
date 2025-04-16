#!/bin/zsh
#
u_flag=false
v_flag=false

while getopts u:v: flag
do
    case "${flag}" in
        u)
          username=${OPTARG}
          u_flag=true
          ;;
        v)
          version=${OPTARG}
          v_flag=true
          ;;
        \?)
          echo "Usage : $0 -u username -v version"
          exit 1
          ;;
    esac
done

if [ "$u_flag" = false ] || [ "$v_flag" = false ]; then
  echo "오류: -u 도커허브계정명 -v 도커이미지버전 옵션을 입력해야 합니다."
  echo "사용법: $0 -u <username> -v <version>"
  exit 1
fi

./gradlew clean build --exclude-task test
sleep 1
docker buildx build --platform linux/amd64 -t ${username}/k8s-demo:${version} --push .
