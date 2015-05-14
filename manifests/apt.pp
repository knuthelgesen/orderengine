stage { 'pre': before => Stage['main'] }

class {
  'apt-init': stage => pre;
}

class apt-init {

#  exec { 'changemirrors' :
#    path => "/usr/bin:/usr/sbin:/bin",
#    cwd => '/',
#    command => 'sudo sed -i -e s/us.archive.ubuntu.com/no.archive.ubuntu.com/g /etc/apt/sources.list',
#    unless => "egrep -c 'no.archive.ubuntu.com' /etc/apt/sources.list"
#  }

  exec { 'apt-get update':
    command => '/usr/bin/apt-get update'
#    require => Exec['changemirrors']
  }

}
