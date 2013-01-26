<?php
	if  ($this->Session->check('Message.auth')) $this->Session->flash('auth');
	echo $this->Form->create('User', array('action' => 'login'));
	echo $this->Form->input('login');
	echo $this->Form->input('password');
	echo $this->Form->end('Login');
?>