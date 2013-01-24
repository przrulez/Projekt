<?php
App::uses('MoviesController', 'Controller');

/**
 * MoviesController Test Case
 *
 */
class MoviesControllerTest extends ControllerTestCase {

/**
 * Fixtures
 *
 * @var array
 */
	public $fixtures = array(
		'app.movie',
		'app.category',
		'app.categories_movie',
		'app.user',
		'app.users_category',
		'app.playlist',
		'app.playlist_movie',
		'app.users_movie'
	);

}
