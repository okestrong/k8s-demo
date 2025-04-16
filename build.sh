#!/bin/zsh
#

help(){
	echo "Usage : $0 -u username -v version"
}

while getopts u:v: flag
do
    case "${flag}" in
        u) username=${OPTARG};;
        v) version=${OPTARG};;
        *) help
          exit 0
          ;;
    esac
done

./gradlew clean build --exclude-task test
sleep 1
docker buildx build --platform linux/amd64 -t ${username}/k8s-demo:${version} --push .
