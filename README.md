Jetty JSP unit test simple example
==================================

It's astonishing how such a difficult-to-find piece of code can be so short. I'd been flirting with the idea of doing true unit tests for JSPs for a long time, years perhaps.

Here are a few sites that helped me on my quest:

- <a href="http://jakarta.apache.org/cactus">Cactus</a>
- <a href="http://www.coderanch.com/t/95556/Testing/Unit-testing-jsp-pages">CodeRanch</a>
- <a href="">Jetty's ServletTester</a>
- <a href="http://httpunit.sourceforge.net/doc/servletunit-intro.html">HTTPUnit's ServletUnit</a>
- <a href="http://tagunit.sourceforge.net">TagUnit</a>
- <a href="http://darkviews.blogspot.com/2007/08/unit-testing-jspinclude.html">Unit Testing jsp:include</a>
- <a href="http://www.jsfunit.org/">JSFUnit</a>
- <a href="http://mockrunner.sourceforge.net/">MockRunner</a>
- <a href="http://htmlunit.sourceforge.net/">HtmlUnit</a>
- <a href="http://www.openqa.org/selenium/">Selenium</a>
- <a href="http://jwebunit.sourceforge.net/">JWebUnit</a>
- <a href="http://httpunit.sourceforge.net/">HTTPUnit</a>
- <a href="http://mojo.codehaus.org/jspc/">Maven JSP Compiler support</a>
- <a href="http://docs.codehaus.org/display/JETTY/ServletTester">Jetty's ServletTester</a>

<a href="http://jspunit.sf.net">JSPUnit</a> looks usable, but I want something closer to a "real" servet environment.

So, knowing how configurable Jetty is generally, and knowing how fast it is even when running as a full-blown container,
I decided to see whether I could get <a href="http://docs.codehaus.org/display/JETTY/ServletTester">Jetty's ServletTester</a> to do the job.
<a href="http://blogs.webtide.com/gregw/entry/unit_test_servlets_with_jetty">This site</a> and <a href="http://www.christianschenk.org/blog/testing-web-applications-with-jetty/">this site</a> present more usages of ServletTester.
<a href="http://www.gisgraphy.com/xref-test/com/gisgraphy/domain/geoloc/service/fulltextsearch/SolrClientTest.html">This code</a> pointed me in the direction of JspServlet.

I won't bore you with the different combinations of configuration I tried, but the result is presented here as a minimalist Hello World maven war project.

Of course, after I'd put this together, and while googling to flesh out this article, I found <a href="http://jsptest.sourceforge.net/">JspTest at Sourceforge</a>.
Needless to say, it does the same stuff. It seems to be using its own implementation of the Servlet API, so perhaps it's further from a real container than I'd like.
Some day I might go to the bother of trying both approaches against some truly horrible JSPs.

What I'd like, and this helps a bit, is true code coverage, ˆ l‡ Clover/Cobertura/Emma.
I imagine it wouldn't be too hard to do code coverage against the generated servlet; but how to report that against the original JSP, preferably with pretty colours?