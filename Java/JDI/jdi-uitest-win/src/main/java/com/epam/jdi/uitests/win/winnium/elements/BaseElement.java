package com.epam.jdi.uitests.win.winnium.elements;

import com.codeborne.selenide.Condition;
import com.epam.commons.LinqUtils;
import com.epam.jdi.uitests.core.annotations.AnnotationsUtil;
import com.epam.jdi.uitests.core.annotations.functions.Functions;
import com.epam.jdi.uitests.core.interfaces.base.IAvatar;
import com.epam.jdi.uitests.core.interfaces.base.IBaseElement;
import com.epam.jdi.uitests.core.interfaces.base.IHasValue;
import com.epam.jdi.uitests.core.settings.JDISettings;
import com.epam.jdi.uitests.core.settings.Layout;
import com.epam.jdi.uitests.win.winnium.actions.ActionInvoker;
import com.epam.jdi.uitests.win.winnium.elements.apiInteract.GetElementModule;
import com.epam.web.matcher.junit.Assert;
import org.openqa.selenium.By;

import java.lang.reflect.Field;
import java.nio.file.Paths;
import java.text.MessageFormat;
import java.util.Arrays;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

import static com.epam.commons.LinqUtils.foreach;
import static com.epam.commons.ReflectionUtils.*;
import static com.epam.commons.StringUtils.namesEqual;
import static com.epam.jdi.uitests.core.settings.JDISettings.exception;
import static com.epam.jdi.uitests.core.settings.JDISettings.shortLogMessagesFormat;

public abstract class BaseElement implements IBaseElement {
    private Object parent;
    private String name;
    private String varName;
    private String typeName;

    protected GetElementModule avatar;
    protected ActionInvoker invoker = new ActionInvoker();

    public BaseElement() {
        avatar = new GetElementModule(this);
    }

    public By getLocator() {
        return avatar.getByLocator();
    }

    @Override
    public GetElementModule getAvatar() {
        return avatar;
    }

    @Override
    public void setParent(Object parent) {
        this.parent = parent;
    }

    @Override
    public void setName(Field field) {
        this.name = AnnotationsUtil.getElementName(field);
        this.varName = field.getName();
    }

    @Override
    public Object getParent() {
        return parent;
    }

    @Override
    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    @Override
    public String getName() {
        return name != null ? name : getTypeName();
    }

    @Override
    public String printContext() {
        if (!(parent instanceof BaseElement))
            return "";

        BaseElement parentBaseElement = (BaseElement)parent;
        By locator = parentBaseElement.avatar.getByLocator();
        if (locator == null)
            return "";

        String parentContext = parentBaseElement.printContext();
        if (parentContext.equals(""))
            return locator.toString();

        return locator.toString() + "; " + parentContext;
    }

    @Override
    public String toString() {
        return MessageFormat.format(shortLogMessagesFormat
                        ? "{1} ''{0}'' ({2}.{3}; {4})"
                        : "Name: ''{0}'', Type: ''{1}'' In: ''{2}'', {4}",
                getName(), getTypeName(), getParentName(), getVarName(), avatar);
    }
    public static BiConsumer<String, Consumer<String>> doActionRule = (text, action) -> {
        if (text == null) return;
        action.accept(text);
    };
    public <T> T asEntity(Class<T> entityClass) {
        try {
            T data = newEntity(entityClass);
            foreach(getFields(this, IHasValue.class), item -> {
                Field field = LinqUtils.first(getFields(data, String.class), f ->
                        namesEqual(f.getName(), item.getName()));
                if (field == null)
                    return;
                try {
                    field.set(data, ((IHasValue) getValueField(item, this)).getValue());
                } catch (Exception ignore) { }
            });
            return data;
        } catch (Exception ex) {
            throw exception("Can't get entity from Form" + getName() + " for class: " + entityClass.getClass());
        }
    }

    private String getParentName() {
        return parent == null ? "" : parent.getClass().getSimpleName();
    }

    private String getTypeName() {
        return (typeName != null) ? typeName : getClass().getSimpleName();
    }

    private String getVarName() {
        return varName != null ? varName : getName();
    }

    public void setAvatar(GetElementModule avatar) {
        this.avatar = avatar;
    }

    public BaseElement setAvatar(IAvatar avatar) {
        this.avatar = (GetElementModule) avatar.copy();
        return this;
    }

    public boolean hasLocator() {
        return avatar.hasLocator();
    }

    public ActionInvoker getInvoker() {
        return invoker;
    }

    @Override
    public void setFunction(Functions function) {
        if (function != Functions.NONE)
            throw JDISettings.exception("Not supported");
    }
    public IBaseElement should(Condition... conditions){
        Arrays.stream(conditions).forEach(condition ->
                Assert.assertEquals(condition.apply(getAvatar().getElement()), true));

        return this;
    }
    public IBaseElement shouldHave(Condition... conditions){
        return should(conditions);
    }
    public IBaseElement shouldBe(Condition... conditions){
        return should(conditions);
    }
    public IBaseElement shouldNot(Condition... conditions){
        Arrays.stream(conditions).forEach(condition ->
                Assert.assertEquals(condition.apply(getAvatar().getElement()), false));
        return this;
    }
    public IBaseElement shouldNotHave(Condition... conditions){
        return shouldNot(conditions);
    }
    public IBaseElement shouldNotBe(Condition... conditions){
        return shouldNot(conditions);
    }

    public boolean verifyLayout(String imgPath) {
        return Layout.verify(getFullImagePath(imgPath));
    }
    private String imgPath;
    public String getImgPath() {
        return imgPath;
    }
    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }
    protected String getFullImagePath(String imgPath) {
        return Paths.get(Layout.rootImagesPath).toAbsolutePath().toString().replace('\\', '/').replaceAll("/*$", "/") + imgPath;
    }


}
