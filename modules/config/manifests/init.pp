class config($my_ip, $other_ip, $constretto_tags) {

  exec { "my_ip":
    path => "/usr/bin:/usr/sbin:/bin",
    cwd => '/',
    command => "echo MY_IP=\"${my_ip}\" >> /etc/environment",
    unless => "egrep -c 'MY_IP' /etc/environment"
  }

  exec { "other_ip":
    path => "/usr/bin:/usr/sbin:/bin",
    cwd => '/',
    command => "echo OTHER_IP=\"${other_ip}\" >> /etc/environment",
    unless => "egrep -c 'OTHER_IP' /etc/environment"
  }

  exec { "constretto_tags":
    path => "/usr/bin:/usr/sbin:/bin",
    cwd => '/',
    command => "echo CONSTRETTO_TAGS=\"${constretto_tags}\" >> /etc/environment",
    unless => "egrep -c 'CONSTRETTO_TAGS' /etc/environment"
  }

  host { 'order1' :
    ip => '192.168.33.101',
  }

  host { 'order2' :
    ip => '192.168.33.102',
  }

  host { 'order3' :
    ip => '192.168.33.103',
  }

}
