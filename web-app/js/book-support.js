$(document).ready(function() {
	
	$('#add_author').click(function() {
		var lastAuthorRow = $(this).closest('tr').prev();
		var newRow = $(lastAuthorRow).clone()
		$(newRow).insertAfter($(lastAuthorRow));
		
		var authorIdTag = $(newRow).find("[name^='contributions']");
		var authorId = $(authorIdTag).attr('id').match(/\[[0-9]*\]/)[0].substr(1,1);
		var newAuthorId = eval(authorId + " + 1");
		$(newRow).find('[id=authorsLabel]').html('');
		$(authorIdTag).attr('id','contributions['+newAuthorId+'].author.id');
		$(authorIdTag).attr('name','contributions['+newAuthorId+'].author.id');
	});
});
