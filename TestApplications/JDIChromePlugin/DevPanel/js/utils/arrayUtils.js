/**
 * Created by Natalia_Grebenshchik on 11/30/2015.
 */

function getItemByFieldContent(array, fieldName, fieldData){

    for (a in array)
        if (array[a][fieldName] === fieldData)
            return array[a];

    return undefined;
}

function getIndex(array, fieldName, fieldData){

    for (a in array)
        if (array[a][fieldName] === fieldData)
            return a;

    return -1;
}
