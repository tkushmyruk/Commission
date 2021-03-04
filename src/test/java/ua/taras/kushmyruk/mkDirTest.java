package ua.taras.kushmyruk;

import org.junit.Test;

import java.io.File;

public class mkDirTest {

    @Test
    public void test(){
        File file = new File("/studentPhotoFiles/probPackage");
        System.out.println(file.mkdir());

    }

}
