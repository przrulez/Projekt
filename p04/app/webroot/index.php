<?php
/**
 * Index
 *
 * The Front Controller for handling every request
 *
 * PHP 5
 *
 * CakePHP(tm) : Rapid Development Framework (http://cakephp.org)
 * Copyright 2005-2012, Cake Software Foundation, Inc. (http://cakefoundation.org)
 *
 * Licensed under The MIT License
 * Redistributions of files must retain the above copyright notice.
 *
 * @copyright     Copyright 2005-2012, Cake Software Foundation, Inc. (http://cakefoundation.org)
 * @link          http://cakephp.org CakePHP(tm) Project
 * @package       app.webroot
 * @since         CakePHP(tm) v 0.2.9
 * @license       MIT License (http://www.opensource.org/licenses/mit-license.php)
 */
/**
 * Use the DS to separate the directories in other defines
 */
 
session_start();

if(@$_GET['str'] == "logout") {
	$_SESSION['logged'] = 0;
	unset($_SESSION['logged']);
	header("Location: p04/..");
}

if(isset($_SESSION["logged"]) && $_SESSION["logged"] == 1) {
	

	if (!defined('DS')) {
		define('DS', DIRECTORY_SEPARATOR);
	}
	/**
	 * These defines should only be edited if you have cake installed in
	 * a directory layout other than the way it is distributed.
	 * When using custom settings be sure to use the DS and do not add a trailing DS.
	 */

	/**
	 * The full path to the directory which holds "app", WITHOUT a trailing DS.
	 *
	 */
	if (!defined('ROOT')) {
		define('ROOT', dirname(dirname(dirname(__FILE__))));
	}
	/**
	 * The actual directory name for the "app".
	 *
	 */
	if (!defined('APP_DIR')) {
		define('APP_DIR', basename(dirname(dirname(__FILE__))));
	}

	/**
	 * The absolute path to the "cake" directory, WITHOUT a trailing DS.
	 *
	 * Un-comment this line to specify a fixed path to CakePHP.
	 * This should point at the directory containing `Cake`.
	 *
	 * For ease of development CakePHP uses PHP's include_path.  If you
	 * cannot modify your include_path set this value.
	 *
	 * Leaving this constant undefined will result in it being defined in Cake/bootstrap.php
	 */
		//define('CAKE_CORE_INCLUDE_PATH', ROOT . DS . 'lib');

	/**
	 * Editing below this line should NOT be necessary.
	 * Change at your own risk.
	 *
	 */
	if (!defined('WEBROOT_DIR')) {
		define('WEBROOT_DIR', basename(dirname(__FILE__)));
	}
	if (!defined('WWW_ROOT')) {
		define('WWW_ROOT', dirname(__FILE__) . DS);
	}

	if (!defined('CAKE_CORE_INCLUDE_PATH')) {
		if (function_exists('ini_set')) {
			ini_set('include_path', ROOT . DS . 'lib' . PATH_SEPARATOR . ini_get('include_path'));
		}
		if (!include ('Cake' . DS . 'bootstrap.php')) {
			$failed = true;
		}
	} else {
		if (!include (CAKE_CORE_INCLUDE_PATH . DS . 'Cake' . DS . 'bootstrap.php')) {
			$failed = true;
		}
	}
	if (!empty($failed)) {
		trigger_error("CakePHP core could not be found.  Check the value of CAKE_CORE_INCLUDE_PATH in APP/webroot/index.php.  It should point to the directory containing your " . DS . "cake core directory and your " . DS . "vendors root directory.", E_USER_ERROR);
	}

	App::uses('Dispatcher', 'Routing');

	$Dispatcher = new Dispatcher();
	$Dispatcher->dispatch(new CakeRequest(), new CakeResponse(array('charset' => Configure::read('App.encoding'))));
} else {
	if(@$_GET['str'] == "login") {
		if(@$_POST['login']=="admin" && @$_POST['password']=="admin") {
			$_SESSION['logged'] = 1;
			header("Location: p04/..");
		} else {
			$message = "<p class='error'>Incorrect login or password!</p>";
			echoForm($message);
		}
	} else {
		echoForm();
	}
}
function echoForm($message = "") {
	echo "<!DOCTYPE html>
				<head>
				  <meta http-equiv='Content-Type' content='text/html; charset=UTF-8' />
				  <title>Movies</title>
				  <link rel='stylesheet' type='text/css' href='/p04/css/cake.generic.css' />
				  <link rel='stylesheet' type='text/css' href='/p04/css/style.css' />
				</head>
				<body>
				  <div id='container'>
					<div id='header'>
						<img src='http://localhost/p04/app/webroot/img/folder_movie.png' alt='folder_movie' />
						<h1>Movie database</h1>";
						if(@$_SESSION['logged'] == 1) {
						echo "<div style='position: absolute; top: 10px; right: 10px;'>
						<a href='index.php?str=logout'>
							<img src='http://localhost/p04/app/webroot/img/logout.png' alt='Logout' title='Logout' />
						</a>
						</div>";
						}
					echo "</div>
					<div id='content' style='text-align: center;'>
						$message
						<form action='index.php?str=login' method='POST' style='width: 200px; margin: 20px auto;'>
							<label for='login'>Login</label>
							<input type='text' name='login' />
							<label for='password'>Password</label>
							<input type='password' name='password' />
							<input type='submit' value='Login' />
						  </form>
					</div>
				  </div>
				</body>
			  </html>";
}