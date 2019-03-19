#!/bin/sh
echo "-------------------------------------------------------------------"
echo "|                     PROVISIONING OF THE BOX                     |"
echo "-------------------------------------------------------------------"

sudo apt-get install -y python3-pip python3-setuptools &&
sudo apt-get autoclean -y &&
sudo apt-get clean -y &&

pip3 install wheel

sudo usermod -aG docker vagrant
alias dockerremove='docker rm -f $(docker ps -aq)'
cd vagrant
docker network create mynet

echo "-------------------------------------------------------------------"
echo "|                     END OF THE PROVISIONING                     |"
echo "-------------------------------------------------------------------"
