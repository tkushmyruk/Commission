<#import "common/common.ftlh" as com>
<@com.main>
    <h2>${faculty.facultyName}</h2>
    <div>You can redact information about faculty</div>
    <form method="post">
        <div><label>Free Places : <input type="number" name="freePlaces" value="${faculty.freePlaces}"> </label></div>
        <div><label>ScholarshipPlaces : <input type="number" name="scholarshipPlaces"
                                               value="${faculty.scholarshipPlaces}"> </label></div>
        <p>redact discipline</p>
        <#list disciplines as discipline>
            <div><label>${discipline.disciplineName} :
                    <input type="text" name="redactedDiscipline" value="${discipline.disciplineName}">
                </label></div>
        </#list>
        <div>
            <div>Add new disciplines and split it by ';'</div>
            <label>Disciplines : <input type="text" name="disciplines"> </label>
        </div>
        <div>Set hulls address</div>
        <div><label>City : <input type="text" name="city" value="${address.city}"> </label></div>
        <div><label>Street : <input type="text" name="street" value="${address.street}"> </label></div>
        <div><label>Building : <input type="text" name="building" value="${address.building}"> </label></div>
        <button type="submit">Save changes</button>
    </form>
</@com.main>