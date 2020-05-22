package com.benrhine.conway.core

import com.benrhine.conway.util.ConwaySpecification

class CytoGridSpec extends ConwaySpecification {

    void "a CytoGrid's state is initialized with the correct number of rows and cols"() {

        when: ""
            final grid = new CytoGrid( rows, cols )
        then: ""
            grid.cols == cols
            grid.rows == rows

            grid.getState().rowKeyList().size() == rows
            grid.getState().columnKeyList().size() == cols
        where: ""
            rows    | cols
            1       | 1
            10      | 10
            25      | 17
            6       | 8
    }
}
