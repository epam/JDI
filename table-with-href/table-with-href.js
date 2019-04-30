$('body').on('mousedown', 'tr[href]', function(e) {
  var click = e.which;
  var href = $(this).attr('href');
  if (href) {
    if (click == 2 || (click == 1 && (e.ctrlKey || e.metaKey))) {
      window.open(href, '_blank');
      window.focus();
    } else if (click == 1) {
      window.location.href = href;
    }
    return true;
  }
});
