<div class="categoriesMovies view">
<h2><?php  echo __('Categories Movie'); ?></h2>
	<dl>
		<dt><?php echo __('Id'); ?></dt>
		<dd>
			<?php echo h($categoriesMovie['CategoriesMovie']['id']); ?>
			&nbsp;
		</dd>
		<dt><?php echo __('Category'); ?></dt>
		<dd>
			<?php echo $this->Html->link($categoriesMovie['Category']['name'], array('controller' => 'categories', 'action' => 'view', $categoriesMovie['Category']['id'])); ?>
			&nbsp;
		</dd>
		<dt><?php echo __('Movie'); ?></dt>
		<dd>
			<?php echo $this->Html->link($categoriesMovie['Movie']['name'], array('controller' => 'movies', 'action' => 'view', $categoriesMovie['Movie']['id'])); ?>
			&nbsp;
		</dd>
		<dt><?php echo __('Created'); ?></dt>
		<dd>
			<?php echo h($categoriesMovie['CategoriesMovie']['created']); ?>
			&nbsp;
		</dd>
		<dt><?php echo __('Modified'); ?></dt>
		<dd>
			<?php echo h($categoriesMovie['CategoriesMovie']['modified']); ?>
			&nbsp;
		</dd>
	</dl>
</div>
<div class="actions">
	<h3><?php echo __('Actions'); ?></h3>
	<ul>
		<li><?php echo $this->Html->link(__('Edit Categories Movie'), array('action' => 'edit', $categoriesMovie['CategoriesMovie']['id'])); ?> </li>
		<li><?php echo $this->Form->postLink(__('Delete Categories Movie'), array('action' => 'delete', $categoriesMovie['CategoriesMovie']['id']), null, __('Are you sure you want to delete # %s?', $categoriesMovie['CategoriesMovie']['id'])); ?> </li>
		<li><?php echo $this->Html->link(__('List Categories Movies'), array('action' => 'index')); ?> </li>
		<li><?php echo $this->Html->link(__('New Categories Movie'), array('action' => 'add')); ?> </li>
		<li><?php echo $this->Html->link(__('List Categories'), array('controller' => 'categories', 'action' => 'index')); ?> </li>
		<li><?php echo $this->Html->link(__('New Category'), array('controller' => 'categories', 'action' => 'add')); ?> </li>
		<li><?php echo $this->Html->link(__('List Movies'), array('controller' => 'movies', 'action' => 'index')); ?> </li>
		<li><?php echo $this->Html->link(__('New Movie'), array('controller' => 'movies', 'action' => 'add')); ?> </li>
	</ul>
</div>
