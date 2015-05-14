import "apt.pp"

class order3 {
  package { 'openjdk-7-jdk':
    ensure => latest
  }

  class { 'config':
    my_ip => '192.168.33.103',
    other_ip => '192.168.33.102',
    constretto_tags => 'ITEST'
  }

  package { "alien":
    ensure => present,
  }

  package { "unzip":
    ensure => present,
  }

  file { ['/u01', '/u01/app', '/u01/app/order']:
    ensure => directory,
  }

  class {'openldap':}

  package { "ldap-utils":
    ensure => latest
  }

	class {'mariadb::server':
		mysql_password => 'password'
	}

}

include order3

