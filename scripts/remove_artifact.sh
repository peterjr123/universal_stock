echo "move artifact"
mv ../*.sh ${ARTIFACT_HOME}

echo "clean deploy folder"
rm -rf  ${DEPLOY_HOME}
mkdir ${DEPLOY_HOME}