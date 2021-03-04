<#import "common/common.ftlh" as c>
<#import "common/login.ftlh" as p>
<@c.main>
    <div class="mb-1">Add new user</div>
    ${message?if_exists}
    <@p.login "/registration" true/>
    <a href="/login">Back to Login page</a>
</@c.main>