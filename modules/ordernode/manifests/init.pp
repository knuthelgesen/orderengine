
class ordernode {

	include apache

	group { "order" :
		ensure => present,
	}

	user { "order" :
		ensure => present,
		managehome => true,
		home => '/home/order',
		shell => '/bin/bash',
		password => '$2y$10$8tIjYvb4kNBjV/pJwolrLu1FmRhWkjxh9ZHoQJLhSOKVYQjmVrbhS',
		groups => 'users',
		gid => order
	}

	exec { 'usermod -p \'$2y$10$8tIjYvb4kNBjV/pJwolrLu1FmRhWkjxh9ZHoQJLhSOKVYQjmVrbhS\' order':
		cwd => '/',
		path => '/bin:/usr/bin:/sbin:/usr/sbin',
		require => User['order'],
		onlyif => "egrep -q '^order:[!]:' /etc/shadow",
	}

	file {
		"/etc/localtime":
		ensure => "/usr/share/zoneinfo/Europe/Oslo"
	}

	package { "tree":
		ensure => present,
	}
	
  package {"python-software-properties" :
      ensure => present
  }

  exec { "oracle-ppa" :
    cwd => '/',
    path => '/bin:/usr/bin:/sbin:/usr/sbin',
    command => 'add-apt-repository -y ppa:webupd8team/java && apt-get update',
    creates => '/etc/apt/sources.list.d/webupd8team-java-precise.list',
    require => Package['python-software-properties']
  }

  package {'debconf-utils':
    ensure => present
  }

  exec { "oracle-license-accepted" :
      cwd => '/',
      path => '/bin:/usr/bin:/sbin:/usr/sbin',
      command => 'echo oracle-java7-installer shared/accepted-oracle-license-v1-1 select true | /usr/bin/debconf-set-selections',
      unless => 'sudo debconf-get-selections | egrep -c shared/accepted-oracle-license-v1-1 ',
      before => Package['oracle-java7-installer'],
      require => Package['debconf-utils']
  }

	package { "oracle-java7-installer" :
		ensure => present,
        require => [Exec['oracle-ppa']]
	}

  package { "oracle-java7-set-default":
      ensure => present,
      require => Package['oracle-java7-installer']
  }

	package { "unzip":
		ensure => present,
	}

  package { "curl":
    ensure => present,
  }

	file { '/var/www':
		ensure => link,
		target => "/vagrant",
		force  => true
	}

	file { ['/u01', '/u01/app', '/u01/app/order']:
		ensure => directory,
	}
}

include order
