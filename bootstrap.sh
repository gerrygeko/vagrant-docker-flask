#!/bin/sh
echo "-------------------------------------------------------------------"
echo "|                     PROVISIONING OF THE BOX                     |"
echo "-------------------------------------------------------------------"
sudo apt-get install -y python3-pip python3-setuptools &&
sudo apt-get autoclean -y &&
sudo apt-get clean -y &&
pip3 install wheel
sudo usermod -aG docker vagrant
echo "-------------------------------------------------------------------"
echo "|                     END OF THE PROVISIONING                     |"
echo "-------------------------------------------------------------------"

cd vagrant