class apache {
  
  package { "apache2":
    ensure => present,
  }

  service { "apache2":
    ensure => running
  }

  file { "/etc/apache2/mods-enabled/proxy.load":
    ensure => link,
    target => "/etc/apache2/mods-available/proxy.load",
	require => Package["apache2"],
	notify => Service["apache2"]
  }

  file { "/etc/apache2/mods-enabled/proxy_http.load":
    ensure => link,
    target => "/etc/apache2/mods-available/proxy_http.load",
	require => Package["apache2"],
	notify => Service["apache2"]
  }

  file { "/etc/apache2/mods-enabled/headers.load":
    ensure => link,
    target => "/etc/apache2/mods-available/headers.load",
	require => Package["apache2"],
	notify => Service["apache2"]
  }

  file { "/etc/apache2/mods-enabled/rewrite.load":
    ensure => link,
    target => "/etc/apache2/mods-available/rewrite.load",
	require => Package["apache2"],
	notify => Service["apache2"]
  }

file { "/etc/apache2/ports.conf":
  ensure => present,
  source => "puppet:///modules/apache/etc/apache2/ports.conf",
  group => root,
  require => [Package["apache2"]],
  notify => Service["apache2"]
}


}
