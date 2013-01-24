<?php
App::uses('AppController', 'Controller');
/**
 * UsersMovies Controller
 *
 * @property UsersMovie $UsersMovie
 */
class UsersMoviesController extends AppController {

/**
 * index method
 *
 * @return void
 */
	public function index() {
		$this->UsersMovie->recursive = 0;
		$this->set('usersMovies', $this->paginate());
	}

/**
 * view method
 *
 * @throws NotFoundException
 * @param string $id
 * @return void
 */
	public function view($id = null) {
		$this->UsersMovie->id = $id;
		if (!$this->UsersMovie->exists()) {
			throw new NotFoundException(__('Invalid users movie'));
		}
		$this->set('usersMovie', $this->UsersMovie->read(null, $id));
	}

/**
 * add method
 *
 * @return void
 */
	public function add() {
		if ($this->request->is('post')) {
			$this->UsersMovie->create();
			if ($this->UsersMovie->save($this->request->data)) {
				$this->Session->setFlash(__('The users movie has been saved'));
				$this->redirect(array('action' => 'index'));
			} else {
				$this->Session->setFlash(__('The users movie could not be saved. Please, try again.'));
			}
		}
		$users = $this->UsersMovie->User->find('list');
		$movies = $this->UsersMovie->Movie->find('list');
		$this->set(compact('users', 'movies'));
	}

/**
 * edit method
 *
 * @throws NotFoundException
 * @param string $id
 * @return void
 */
	public function edit($id = null) {
		$this->UsersMovie->id = $id;
		if (!$this->UsersMovie->exists()) {
			throw new NotFoundException(__('Invalid users movie'));
		}
		if ($this->request->is('post') || $this->request->is('put')) {
			if ($this->UsersMovie->save($this->request->data)) {
				$this->Session->setFlash(__('The users movie has been saved'));
				$this->redirect(array('action' => 'index'));
			} else {
				$this->Session->setFlash(__('The users movie could not be saved. Please, try again.'));
			}
		} else {
			$this->request->data = $this->UsersMovie->read(null, $id);
		}
		$users = $this->UsersMovie->User->find('list');
		$movies = $this->UsersMovie->Movie->find('list');
		$this->set(compact('users', 'movies'));
	}

/**
 * delete method
 *
 * @throws MethodNotAllowedException
 * @throws NotFoundException
 * @param string $id
 * @return void
 */
	public function delete($id = null) {
		if (!$this->request->is('post')) {
			throw new MethodNotAllowedException();
		}
		$this->UsersMovie->id = $id;
		if (!$this->UsersMovie->exists()) {
			throw new NotFoundException(__('Invalid users movie'));
		}
		if ($this->UsersMovie->delete()) {
			$this->Session->setFlash(__('Users movie deleted'));
			$this->redirect(array('action' => 'index'));
		}
		$this->Session->setFlash(__('Users movie was not deleted'));
		$this->redirect(array('action' => 'index'));
	}
}
