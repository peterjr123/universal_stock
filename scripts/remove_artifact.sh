echo "move artifact"
mv ../*.sh /home/ubuntu/artifacts/

echo "clean deploy folder"
rm -rf  /home/ubuntu/deploy/
mkdir /home/ubuntu/deploy/