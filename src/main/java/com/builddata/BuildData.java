package com.builddata;

import com.builddata.models.Section;
import com.builddata.models.Variable;

import java.util.ArrayList;

public interface BuildData {
    final ArrayList<Variable> VARIABLES = new ArrayList<Variable>();
    final ArrayList<Section> SECTIONS = new ArrayList<Section>();
    final ArrayList<Character> ILLEGAL_CHARACTERS = new ArrayList<>();

}
