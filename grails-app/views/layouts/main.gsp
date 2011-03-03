<!DOCTYPE html>
<html>
    <head>
        <title><g:layoutTitle default="Grails" /></title>
        <link rel="stylesheet" href="${resource(dir:'css',file:'main.css')}" />
        <link rel="shortcut icon" href="${resource(dir:'images',file:'favicon.ico')}" type="image/x-icon" />
        <g:layoutHead />
        <g:javascript library="application" />
        <g:javascript library="jquery" plugin="jquery"/>
    </head>
    <body>
        <div id="spinner" class="spinner" style="display:none;">
            <img src="${resource(dir:'images',file:'spinner.gif')}" alt="${message(code:'spinner.alt',default:'Loading...')}" />
        </div>
        <div id="grailsLogo"><a href="http://grails.org"><img src="${resource(dir:'images',file:'grails_logo.png')}" alt="Grails" border="0" /></a></div>
        <div id="pageBody">
        	<h1>Welcome to Grails</h1>
            <div id="controllerList" class="dialog">
                <g:each var="c" in="${grailsApplication.controllerClasses.sort { it.fullName } }">
                   <g:link controller="${c.logicalPropertyName}">${c.fullName}</g:link></li>
                </g:each>
            </div>
        </div>
        <g:layoutBody />
        <div id="copyrightNotice" style="width:100%;clear:both;padding-top:10px;text-align:center" >
        	&copy; 2010 - <g:thisYear/>, Trond Valen     	
        </div>
    </body>
</html>