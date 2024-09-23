echo "move artifact"
mv ${DEPLOY_HOME}/*.war ${ARTIFACT_HOME}

echo "clean deploy folder"
rm -rf  ${DEPLOY_HOME}
mkdir ${DEPLOY_HOME}