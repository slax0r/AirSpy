package com.mac.airspy.utils;

import java.io.Serializable;

public class Vector3D implements Serializable {

    private static final long serialVersionUID = -7026354578113311982L;

    private final double x, y, z;

    public Vector3D(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Vector3D(Vector3D vector3D) {
        this.x = vector3D.x;
        this.y = vector3D.y;
        this.z = vector3D.z;
    }


    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getZ() {
        return z;
    }

    public double[] getCoords() {
        return (new double[] { x, y,z });
    }

    public boolean equals(Object o) {
        if (o instanceof Vector3D) {
            Vector3D p = (Vector3D) o;
            return p.x == x && p.y == y && p.z == z;
        }
        return false;
    }

    public int hashCode() {
        return (int) (x + y + z);
    }

    public Vector3D add(Vector3D other) {
        double x = this.x + other.x;
        double y = this.y + other.y;
        double z = this.z + other.z;
        return new Vector3D(x, y, z);
    }

    public Vector3D subtract(Vector3D other) {
        double x = this.x - other.x;
        double y = this.y - other.y;
        double z = this.z - other.z;
        return new Vector3D(x, y, z);
    }

    public Vector3D multiply(double value) {
        return new Vector3D(value * x, value * y, value * z);
    }

    public Vector3D crossProduct(Vector3D other) {
        double x = this.y * other.z - other.y * this.z;
        double y = this.z * other.x - other.z * this.x;
        double z = this.x * other.y - other.x * this.y;
        return new Vector3D(x, y, z);
    }

    public double dotProduct(Vector3D other) {
        return other.x * x + other.y * y + other.z * z;
    }

    public Vector3D normalize() {
        double magnitude = Math.sqrt(dotProduct(this));
        return new Vector3D(x / magnitude, y / magnitude, z / magnitude);
    }

    public double level() {
        return Math.sqrt(dotProduct(this));
    }

    public Vector3D modulate(Vector3D other) {
        double x = this.x * other.x;
        double y = this.y * other.y;
        double z = this.z * other.z;
        return new Vector3D(x, y, z);
    }

    public double length() {
        return Math.sqrt(x * x + y * y + z * z);
    }

    public String toString() {
        return (new StringBuffer("[Vector3D x:")).append(x).append(" y:")
                .append(y).append(" z:").append(z).append("]").toString();
    }

}