<?php
App::uses('AppController', 'Controller');
/**
 * UsersCategories Controller
 *
 * @property UsersCategory $UsersCategory
 */
class UsersCategoriesController extends AppController {

/**
 * index method
 *
 * @return void
 */
	public function index() {
		$this->UsersCategory->recursive = 0;
		$this->set('usersCategories', $this->paginate());
	}

/**
 * view method
 *
 * @throws NotFoundException
 * @param string $id
 * @return void
 */
	public function view($id = null) {
		$this->UsersCategory->id = $id;
		if (!$this->UsersCategory->exists()) {
			throw new NotFoundException(__('Invalid users category'));
		}
		$this->set('usersCategory', $this->UsersCategory->read(null, $id));
	}

/**
 * add method
 *
 * @return void
 */
	public function add() {
		if ($this->request->is('post')) {
			$this->UsersCategory->create();
			if ($this->UsersCategory->save($this->request->data)) {
				$this->Session->setFlash(__('The users category has been saved'));
				$this->redirect(array('action' => 'index'));
			} else {
				$this->Session->setFlash(__('The users category could not be saved. Please, try again.'));
			}
		}
		$users = $this->UsersCategory->User->find('list');
		$categories = $this->UsersCategory->Category->find('list');
		$this->set(compact('users', 'categories'));
	}

/**
 * edit method
 *
 * @throws NotFoundException
 * @param string $id
 * @return void
 */
	public function edit($id = null) {
		$this->UsersCategory->id = $id;
		if (!$this->UsersCategory->exists()) {
			throw new NotFoundException(__('Invalid users category'));
		}
		if ($this->request->is('post') || $this->request->is('put')) {
			if ($this->UsersCategory->save($this->request->data)) {
				$this->Session->setFlash(__('The users category has been saved'));
				$this->redirect(array('action' => 'index'));
			} else {
				$this->Session->setFlash(__('The users category could not be saved. Please, try again.'));
			}
		} else {
			$this->request->data = $this->UsersCategory->read(null, $id);
		}
		$users = $this->UsersCategory->User->find('list');
		$categories = $this->UsersCategory->Category->find('list');
		$this->set(compact('users', 'categories'));
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
		$this->UsersCategory->id = $id;
		if (!$this->UsersCategory->exists()) {
			throw new NotFoundException(__('Invalid users category'));
		}
		if ($this->UsersCategory->delete()) {
			$this->Session->setFlash(__('Users category deleted'));
			$this->redirect(array('action' => 'index'));
		}
		$this->Session->setFlash(__('Users category was not deleted'));
		$this->redirect(array('action' => 'index'));
	}
}
