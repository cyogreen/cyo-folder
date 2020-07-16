<html>
    <head>
        <title>Farmocart</title>
        <?php include 'header.php';?>
    </head>
    <body>
        <header class="head">
            <a href="http://farmocart.com">Farmocart.com</a>
        </header>
        <section class="sec1">
            <div class="task_one">
                <h4>Add employee</h4>
            </div>
            
            <div class="task_adempl">
                <div class="ins_suc"></div>
            <form action="addemployeesub.php" method="post" enctype="multipart/form-data">
                <div class="form-group">
                    <label for="ename">Employee name:</label>
                    <input type="text" class="form-control" id="ename" placeholder="employee name" name="ename">
                    </div>
                    <div class="form-group">
                    <label for="emobile">Employee mobile:</label>
                    <input type="text" class="form-control" id="emobile" placeholder="Employee mobile" name="emobile">
                    </div>
                    <div class="form-group">
                    <label for="email">Employee email:</label>
                    <input type="text" class="form-control" id="email" placeholder="employee email" name="email">
                    </div>
                    <div class="form-group">
                        <label for="erole">Employee role:</label>
                        <select class="form-control" id="erole" name="erole">
                            <option>Admin</option>
                            <option>Sub-Admin</option>
                            <option>Cashier</option>
                            <option>Productionist</option>
                            <option>Data entry</option>
                            </select>
                            </div>
                            <div class="form-group">
                    <label for="bname">Bank Name:</label>
                    <input type="text" class="form-control" id="bname" placeholder="Bank name" name="bname">
                    </div>
                          <div class="form-group">
                    <label for="acno">Account number:</label>
                    <input type="text" class="form-control" id="acno" placeholder="Account number" name="acno">
                    </div>
                       <div class="form-group">
                    <label for="ifsc">IFSC Code:</label>
                    <input type="text" class="form-control" id="ifsc" placeholder="IFSC Code" name="ifsc">
                    </div>
                            
                    <input type="submit" class="btn btn-primary" name="esubmit" id="esubmit" value="submit"> 
            </form>
           
            </div>
        </section>
        
        <script>
        /*    $(document).ready(function(){
                $('#esubmit').click(function(){
                    
                })
            })*/
        </script>
    </body>
    </html>