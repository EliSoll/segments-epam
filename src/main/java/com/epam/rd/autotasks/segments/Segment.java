package com.epam.rd.autotasks.segments;

import static java.lang.Math.abs;
import static java.lang.Math.sqrt;
import static java.lang.StrictMath.pow;

class Segment {
    private final Point start;
    private final Point end;

    public Segment(Point start, Point end) {
        if (start == null || end == null)
            throw new IllegalArgumentException("Arguments can't be null");
        if (start.getX() == end.getX() && start.getY() == end.getY())
            throw new IllegalArgumentException("The points must differ");
        this.start = start;
        this.end = end;
    }

    double length() {
        double xDistSqr = Math.pow(start.getX() - end.getX(), 2);
        double yDistSqr = Math.pow(start.getY() - end.getY(), 2);
        return Math.sqrt(xDistSqr + yDistSqr);
    }

    Point middle() {
        double x1 = start.getX();
        double x2 = end.getX();
        double y1 = start.getY();
        double y2 = end.getY();
        return new Point((x1 + x2) / 2, (y1 + y2) / 2);
    }

    Point intersection(Segment another) {
        double x11 = start.getX();
        double x12 = end.getX();
        double y11 = start.getY();
        double y12 = end.getY();
        double x21 = another.start.getX();
        double x22 = another.end.getX();
        double y21 = another.start.getY();
        double y22 = another.end.getY();

        double dx1 = x12-x11;
        double dy1 = y12-y11;
        double dx2 = x22-x21;
        double dy2 = y22-y21;
        double dxx = x11-x21;
        double dyy = y11-y21;
        double div, t, s;

        div = dy2*dx1-dx2*dy1;
        if (Math.abs(div) < 1.0e-10)
            return null;  //collinear

        t = (dx1*dyy-dy1*dxx) / div;
        if (t < 0 || t > 1.0)
            return null; //intersection outside the 1 segment

        s = (dx2*dyy-dy2*dxx) / div;
        if (s < 0 || s > 1.0)
            return null;  //intersection outside the 2 segment
        return new Point(x11 + s * dx1, y11 + s * dy1);
    }

}
