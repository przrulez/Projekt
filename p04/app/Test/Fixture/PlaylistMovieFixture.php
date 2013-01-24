<?php
/**
 * PlaylistMovieFixture
 *
 */
class PlaylistMovieFixture extends CakeTestFixture {

/**
 * Fields
 *
 * @var array
 */
	public $fields = array(
		'id' => array('type' => 'integer', 'null' => false, 'default' => null, 'key' => 'primary'),
		'playlist_id' => array('type' => 'integer', 'null' => false, 'default' => null),
		'movie_id' => array('type' => 'integer', 'null' => false, 'default' => null),
		'created' => array('type' => 'date', 'null' => true, 'default' => null),
		'modified' => array('type' => 'date', 'null' => true, 'default' => null),
		'indexes' => array(
			'PRIMARY' => array('column' => 'id', 'unique' => 1)
		),
		'tableParameters' => array('charset' => 'utf8', 'collate' => 'utf8_polish_ci', 'engine' => 'InnoDB')
	);

/**
 * Records
 *
 * @var array
 */
	public $records = array(
		array(
			'id' => 1,
			'playlist_id' => 1,
			'movie_id' => 1,
			'created' => '2012-11-05',
			'modified' => '2012-11-05'
		),
	);

}
