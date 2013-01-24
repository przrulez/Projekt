<div class="usersMovies form">
<?php echo $this->Form->create('UsersMovie'); ?>
	<fieldset>
		<legend><?php echo __('Edit Users Movie'); ?></legend>
	<?php
		echo $this->Form->input('id');
		echo $this->Form->input('users_id');
		echo $this->Form->input('movies_id');
	?>
	</fieldset>
<?php echo $this->Form->end(__('Submit')); ?>
</div>
<div class="actions">
	<h3><?php echo __('Actions'); ?></h3>
	<ul>

		<li><?php echo $this->Form->postLink(__('Delete'), array('action' => 'delete', $this->Form->value('UsersMovie.id')), null, __('Are you sure you want to delete # %s?', $this->Form->value('UsersMovie.id'))); ?></li>
		<li><?php echo $this->Html->link(__('List Users Movies'), array('action' => 'index')); ?></li>
		<li><?php echo $this->Html->link(__('List Users'), array('controller' => 'users', 'action' => 'index')); ?> </li>
		<li><?php echo $this->Html->link(__('New Users'), array('controller' => 'users', 'action' => 'add')); ?> </li>
		<li><?php echo $this->Html->link(__('List Movies'), array('controller' => 'movies', 'action' => 'index')); ?> </li>
		<li><?php echo $this->Html->link(__('New Movies'), array('controller' => 'movies', 'action' => 'add')); ?> </li>
	</ul>
</div>
