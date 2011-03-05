<%@ page import="no.steria.trv.Book" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'book.label', default: 'Book')}" />        
        <title><g:message code="default.create.label" args="[entityName]" /></title>          
    </head>
    <body>
        <g:javascript src="book-support.js"/>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></span>
            <span class="menuButton"><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></span>
        </div>
        <div class="body">
            <h1><g:message code="default.create.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${bookInstance}">
            <div class="errors">
                <g:renderErrors bean="${bookInstance}" as="list"/>
            </div>
            </g:hasErrors>            	        
            <g:form action="save" >
                <div class="dialog">
                    <table>
                        <tbody>
                        	<tr class="prop">
                                <td class="name">
                                    <label for="title"><g:message code="book.title.label" default="Title" /></label>
                                </td>
                                <td colspan="2" class="value ${hasErrors(bean: bookInstance, field: 'title', 'errors')}">
                                    <g:textField name="title" value="${bookInstance?.title}" />
                                </td>
                            </tr>                        
                            <tr class="prop">
                                <td class="name" id="authorsLabel">
                                    <g:message code="book.authors.label" default="Authors" />
                                </td>
                                <td class="value">               		
                                    <g:select name="contributions[0].author.id" from="${no.steria.trv.Author.list()}" 
                                    	optionKey="id" value="${contributions?.getAt(0)?.author?.id}"                                    	
                                    	noSelection="${['null':message(code:'no_author.select',default:'No author')]}"/>
                                </td>                                                         
                            </tr>
                            <tr class="prop">
                            	<td class="name" id="add_author">
                            		<a href="#"><g:message code="book.add_authors.label" default="Add authors"/></a>
                            	</td>
                            	<td>&nbsp;</td>
                            </tr>
                        </tbody>
                    </table>
                </div>
                <div class="buttons">
                    <span class="button"><g:submitButton name="create" class="save" value="${message(code: 'default.button.create.label', default: 'Create')}" /></span>
                </div>
            </g:form>
        </div>    
    </body>
</html>
