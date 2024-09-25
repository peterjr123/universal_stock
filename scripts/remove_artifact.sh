CATALINA_HOME=/home/ubuntu/apache-tomcat-10.1.20
DEPLOY_HOME=/home/ubuntu/deploy/
ARTIFACT_HOME=/home/ubuntu/artifacts/

echo "move artifact"
mv ${DEPLOY_HOME}/*.war ${ARTIFACT_HOME}

echo "clean deploy folder"
rm -rf  ${DEPLOY_HOME}
mkdir ${DEPLOY_HOME}