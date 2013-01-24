<?php
App::uses('AppModel', 'Model');
/**
 * PlaylistMovie Model
 *
 * @property Playlist $Playlist
 * @property Movie $Movie
 */
class PlaylistMovie extends AppModel {

/**
 * Display field
 *
 * @var string
 */
	public $displayField = 'id';


	//The Associations below have been created with all possible keys, those that are not needed can be removed

/**
 * belongsTo associations
 *
 * @var array
 */
	public $belongsTo = array(
		'Playlist' => array(
			'className' => 'Playlist',
			'foreignKey' => 'playlist_id',
			'conditions' => '',
			'fields' => '',
			'order' => ''
		),
		'Movie' => array(
			'className' => 'Movie',
			'foreignKey' => 'movie_id',
			'conditions' => '',
			'fields' => '',
			'order' => ''
		)
	);
}
