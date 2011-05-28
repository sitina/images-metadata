CREATE TABLE `imagemetadata` (
 `id` bigint(20) NOT NULL,
 `aperture` float DEFAULT NULL,
 `focalLength` float DEFAULT NULL,
 `height` bigint(20) DEFAULT NULL,
 `name` varchar(255) DEFAULT NULL,
 `path` varchar(255) DEFAULT NULL,
 `taken` datetime DEFAULT NULL,
 `width` bigint(20) DEFAULT NULL,
 `size` bigint(20) DEFAULT NULL,
 PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;


CREATE TABLE `hibernate_sequences` (
 `sequence_name` varchar(255) DEFAULT NULL,
 `sequence_next_hi_value` int(11) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

CREATE TABLE `imagedirectory` (
 `id` bigint(20) NOT NULL,
 `filesCount` bigint(20) DEFAULT NULL,
 `fullPath` varchar(255) DEFAULT NULL,
 `name` varchar(255) DEFAULT NULL,
 `processed` bit(1) NOT NULL,
 PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;