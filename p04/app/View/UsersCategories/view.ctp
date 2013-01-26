<div class="usersCategories view">
<h2><?php  echo __('Users Category'); ?></h2>
	<dl>
		<dt><?php echo __('Id'); ?></dt>
		<dd>
			<?php echo h($usersCategory['UsersCategory']['id']); ?>
			&nbsp;
		</dd>
		<dt><?php echo __('User'); ?></dt>
		<dd>
			<?php echo $this->Html->link($usersCategory['User']['id'], array('controller' => 'users', 'action' => 'view', $usersCategory['User']['id'])); ?>
			&nbsp;
		</dd>
		<dt><?php echo __('Category'); ?></dt>
		<dd>
			<?php echo $this->Html->link($usersCategory['Category']['name'], array('controller' => 'categories', 'action' => 'view', $usersCategory['Category']['id'])); ?>
			&nbsp;
		</dd>
		<dt><?php echo __('Created'); ?></dt>
		<dd>
			<?php echo h($usersCategory['UsersCategory']['created']); ?>
			&nbsp;
		</dd>
		<dt><?php echo __('Modified'); ?></dt>
		<dd>
			<?php echo h($usersCategory['UsersCategory']['modified']); ?>
			&nbsp;
		</dd>
	</dl>
</div>
<div class="actions">
	<h3><?php echo __('Actions'); ?></h3>
	<ul>
		<li><?php echo $this->Html->link(__('Edit Users Category'), array('action' => 'edit', $usersCategory['UsersCategory']['id'])); ?> </li>
		<li><?php echo $this->Form->postLink(__('Delete Users Category'), array('action' => 'delete', $usersCategory['UsersCategory']['id']), null, __('Are you sure you want to delete # %s?', $usersCategory['UsersCategory']['id'])); ?> </li>
		<li><?php echo $this->Html->link(__('List Users Categories'), array('action' => 'index')); ?> </li>
		<li><?php echo $this->Html->link(__('New Users Category'), array('action' => 'add')); ?> </li>
		<li><?php echo $this->Html->link(__('List Users'), array('controller' => 'users', 'action' => 'index')); ?> </li>
		<li><?php echo $this->Html->link(__('New User'), array('controller' => 'users', 'action' => 'add')); ?> </li>
		<li><?php echo $this->Html->link(__('List Categories'), array('controller' => 'categories', 'action' => 'index')); ?> </li>
		<li><?php echo $this->Html->link(__('New Category'), array('controller' => 'categories', 'action' => 'add')); ?> </li>
	</ul>
</div>
