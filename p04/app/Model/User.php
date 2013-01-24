<?php
App::uses('AppModel', 'Model');
/**
 * User Model
 *
 * @property Playlist $Playlist
 * @property Category $Category
 * @property Movie $Movie
 */
class User extends AppModel {

/**
 * Display field
 *
 * @var string
 */
	public $displayField = 'login';


	//The Associations below have been created with all possible keys, those that are not needed can be removed

/**
 * hasMany associations
 *
 * @var array
 */
	public $hasMany = array(
		'Playlist' => array(
			'className' => 'Playlist',
			'foreignKey' => 'user_id',
			'dependent' => false,
			'conditions' => '',
			'fields' => '',
			'order' => '',
			'limit' => '',
			'offset' => '',
			'exclusive' => '',
			'finderQuery' => '',
			'counterQuery' => ''
		)
	);


/**
 * hasAndBelongsToMany associations
 *
 * @var array
 */
	public $hasAndBelongsToMany = array(
		'Category' => array(
			'className' => 'Category',
			'joinTable' => 'users_categories',
			'foreignKey' => 'user_id',
			'associationForeignKey' => 'category_id',
			'unique' => 'keepExisting',
			'conditions' => '',
			'fields' => '',
			'order' => '',
			'limit' => '',
			'offset' => '',
			'finderQuery' => '',
			'deleteQuery' => '',
			'insertQuery' => ''
		),
		'Movie' => array(
			'className' => 'Movie',
			'joinTable' => 'users_movies',
			'foreignKey' => 'user_id',
			'associationForeignKey' => 'movie_id',
			'unique' => 'keepExisting',
			'conditions' => '',
			'fields' => '',
			'order' => '',
			'limit' => '',
			'offset' => '',
			'finderQuery' => '',
			'deleteQuery' => '',
			'insertQuery' => ''
		)
	);

}
