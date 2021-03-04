<#import "common/common.ftlh" as c>
<#import "common/login.ftlh" as p>
<@c.main>
    ${message?if_exists}
    <@p.login "/login" false />
    <@p.logout/>
</@c.main>