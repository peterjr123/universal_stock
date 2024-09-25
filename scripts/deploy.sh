CATALINA_HOME=/home/ubuntu/apache-tomcat-10.1.20
DEPLOY_HOME=/home/ubuntu/deploy/
ARTIFACT_HOME=/home/ubuntu/artifacts/

# copy war file to CATALINA_HOME
echo "execute deploy.sh"

filename=`basename ../*.war`
echo "copy ${filename} to ${CATALINA_HOME}"
cp ../*.war ${CATALINA_HOME}/webapps

# print /webapps
echo "copied ${filename} to ${CATALINA_HOME}"
ls ${CATALINA_HOME}/webapps

# copy the deployed file and change name to ROOT.war
echo $filename
yes | cp -f ${CATALINA_HOME}/webapps/${filename} ${CATALINA_HOME}/webapps/ROOT.war
echo "updated ROOT.war"
ls -l ${CATALINA_HOME}/webapps