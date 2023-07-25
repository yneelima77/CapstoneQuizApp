    // JavaScript function to scroll to the top of the page
    function scrollToTop() {
      window.scrollTo({ top: 0, behavior: 'smooth' });
    }

    // Show the button when the user scrolls down 20px from the top of the document
    window.onscroll = function() {
      scrollFunction();
    };

    function scrollFunction() {
      var scrollToTopBtn = document.getElementById("scrollToTopBtn");
      if (document.body.scrollTop > 20 || document.documentElement.scrollTop > 20) {
        scrollToTopBtn.style.display = "block";
      } else {
        scrollToTopBtn.style.display = "none";
      }
    }
    document.getElementById("scrollToTopBtn").addEventListener("click", scrollToTop);
