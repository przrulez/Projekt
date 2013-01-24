<?php
App::uses('AppModel', 'Model');
/**
 * UsersMovie Model
 *
 * @property Users $Users
 * @property Movies $Movies
 */
class UsersMovie extends AppModel {


	//The Associations below have been created with all possible keys, those that are not needed can be removed

/**
 * belongsTo associations
 *
 * @var array
 */
	public $belongsTo = array(
		'Users' => array(
			'className' => 'Users',
			'foreignKey' => 'users_id',
			'conditions' => '',
			'fields' => '',
			'order' => ''
		),
		'Movies' => array(
			'className' => 'Movies',
			'foreignKey' => 'movies_id',
			'conditions' => '',
			'fields' => '',
			'order' => ''
		)
	);
}
