Vagrant.configure("2") do |config|
  config.vm.box = "antapos/ubuntu-trusty64-jdk8-maven"
  config.vm.network "forwarded_port", guest: 8080, host: 8080
  config.vm.box_version = "0.1"
  config.vm.provision "shell", path: "vagrant_provision.sh"
end
