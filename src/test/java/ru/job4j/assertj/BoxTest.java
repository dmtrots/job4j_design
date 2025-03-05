package ru.job4j.assertj;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.withPrecision;

class BoxTest {
    @Test
    void isThisSphere() {
        Box box = new Box(0, 10);
        String name = box.whatsThis();
        assertThat(name).isEqualTo("Sphere");
    }

    @Test
    void isThisUnknownObject() {
        Box box = new Box(5, 10);
        String name = box.whatsThis();
        assertThat(name).isEqualTo("Unknown object");
    }

    @Test
    void NUmberOfVerticesIsFour() {
        Box box = new Box(4, 6);
        int vertex = box.getNumberOfVertices();
        assertThat(vertex).isEqualTo(4);
    }

    @Test
    void NUmberOfVerticesIsFive() {
        Box box = new Box(5, 7);
        int vertex = box.getNumberOfVertices();
        assertThat(vertex).isEqualTo(-1);
    }

    @Test
    void FigureExists() {
        Box box = new Box(8, 12);
        boolean figure = box.isExist();
        assertThat(figure).isTrue();
    }

    @Test
    void FigureDoesNotExist() {
        Box box = new Box(5, 10);
        boolean figure = box.isExist();
        assertThat(figure).isFalse();
    }

    @Test
    void AreaOfSphere() {
        Box box = new Box(0, 4);
        double square = box.getArea();
        assertThat(square).isCloseTo(201.06d, withPrecision(0.01d));
    }

    @Test
    void AreaOfTetrahedron() {
        Box box = new Box(4, 2);
        double square = box.getArea();
        assertThat(square).isCloseTo(7d, withPrecision(0.1d));
    }
}