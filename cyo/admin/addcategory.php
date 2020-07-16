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
                <h4>Add Category</h4>
            </div>
            
            <div class="task_categ">
                
                <form action="addcategorysub.php" method="post" enctype="multipart/form-data">
                     <div class="form-group">
                    <label for="catname">Category name:</label>
                    <input type="text" class="form-control" id="catname" placeholder="Category name" name="catname">
                    </div>
                    <div class="form-group">
                    <label for="catimg">Category image:</label>
                    <input type="file" class="form-control" id="catimg" placeholder="Category image" name="catimg">
                    </div>
                    <input type="submit" name="catsubmit" id="catsubmit" class="btn btn-primary" value="Submit">
                </form>
            </div>
            </section>
    </body>
</html>