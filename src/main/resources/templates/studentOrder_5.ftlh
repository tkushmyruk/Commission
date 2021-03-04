<#import "common/common.ftlh" as com>
<@com.main>
    <h2>${facultyName}</h2>
    <div>Add Certification Information</div>
    <form method="post" enctype="multipart/form-data">
    <div><label>Certificate number: <input type="text" name="certificateNumber"></label></div>
    <div><label>School name : <input type="text" name="schoolName"></label></div>
    <div><label>End date of school : <input type="date" name="endDate"></label></div>
    <#list disciplines as discipline>
        <div><label>${discipline.disciplineName} :  <input type="number" max="200" name="scores"></label></div>
    </#list>
        <div>
            <p>Passport Photo</p>
            <input type="file" name="certificatePhotofile" id="passport">
        </div>
        <br/>
        <button type="submit">Save</button>
    </form>
</@com.main>