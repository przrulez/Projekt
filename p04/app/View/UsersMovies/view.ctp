<div class="usersMovies view">
<h2><?php  echo __('Users Movie'); ?></h2>
	<dl>
		<dt><?php echo __('Id'); ?></dt>
		<dd>
			<?php echo h($usersMovie['UsersMovie']['id']); ?>
			&nbsp;
		</dd>
		<dt><?php echo __('Users'); ?></dt>
		<dd>
			<?php echo $this->Html->link($usersMovie['Users']['id'], array('controller' => 'users', 'action' => 'view', $usersMovie['Users']['id'])); ?>
			&nbsp;
		</dd>
		<dt><?php echo __('Movies'); ?></dt>
		<dd>
			<?php echo $this->Html->link($usersMovie['Movies']['name'], array('controller' => 'movies', 'action' => 'view', $usersMovie['Movies']['id'])); ?>
			&nbsp;
		</dd>
	</dl>
</div>
<div class="actions">
	<h3><?php echo __('Actions'); ?></h3>
	<ul>
		<li><?php echo $this->Html->link(__('Edit Users Movie'), array('action' => 'edit', $usersMovie['UsersMovie']['id'])); ?> </li>
		<li><?php echo $this->Form->postLink(__('Delete Users Movie'), array('action' => 'delete', $usersMovie['UsersMovie']['id']), null, __('Are you sure you want to delete # %s?', $usersMovie['UsersMovie']['id'])); ?> </li>
		<li><?php echo $this->Html->link(__('List Users Movies'), array('action' => 'index')); ?> </li>
		<li><?php echo $this->Html->link(__('New Users Movie'), array('action' => 'add')); ?> </li>
		<li><?php echo $this->Html->link(__('List Users'), array('controller' => 'users', 'action' => 'index')); ?> </li>
		<li><?php echo $this->Html->link(__('New Users'), array('controller' => 'users', 'action' => 'add')); ?> </li>
		<li><?php echo $this->Html->link(__('List Movies'), array('controller' => 'movies', 'action' => 'index')); ?> </li>
		<li><?php echo $this->Html->link(__('New Movies'), array('controller' => 'movies', 'action' => 'add')); ?> </li>
	</ul>
</div>
