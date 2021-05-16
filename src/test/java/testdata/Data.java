package testdata;

import org.testng.annotations.DataProvider;

public class Data {

    @DataProvider(name = "ValidUserDetails")
    public Object[][] getValidUserData() {
        return new Object[][]
                {
                        {"neeraj.bakhtani@gmail.com", "Neeraj@19a"},
                };

    }

    @DataProvider(name = "InValidUserDetails")
    public Object[][] getInvalidUserData() {
        return new Object[][]
                {
                        {"unregisteredemail123@gmail.com", "xyz"},
                };

    }

}
