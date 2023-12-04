package gr.aueb.carpooling.model.test.util;

import org.junit.jupiter.api.Assertions;

public class BasicEqualTester<T> {

    private T objectUnderTest;


    public void otherObjectIsNull() {
        Assertions.assertNotEquals(null, getObjectUnderTest());

    }

    public void bothObjectsHaveNoState(Object other) {
        Assertions.assertEquals(objectUnderTest, other);
        Assertions.assertEquals(objectUnderTest.hashCode(), other.hashCode());
    }

    public void otherObjectsHasNoState(Object other) {
        Assertions.assertNotEquals(objectUnderTest, other);
        Assertions.assertNotEquals(objectUnderTest.hashCode(), other.hashCode());
    }


    public void otherObjectIsOfDifferentType(Object other) {
        Assertions.assertNotEquals(objectUnderTest, other);
    }

    public void sameReferences(Object other) {
        Assertions.assertEquals(objectUnderTest, other);
        Assertions.assertEquals(objectUnderTest.hashCode(), other.hashCode());
    }


    public void bothObjectsHaveSameState(Object other) {
        Assertions.assertEquals(objectUnderTest, other);
        Assertions.assertEquals(objectUnderTest.hashCode(), other.hashCode());
    }

    public void objectsHaveDifferentState(Object other) {
        Assertions.assertNotEquals(objectUnderTest, other);
        Assertions.assertNotEquals(objectUnderTest.hashCode(), other.hashCode());
    }


    public void setObjectUnderTest(T objectUnderTest) {
        this.objectUnderTest = objectUnderTest;
    }

    public T getObjectUnderTest() {
        return objectUnderTest;
    }


}
