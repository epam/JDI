using System;
using System.Collections.Generic;
using JDI_Core.Interfaces.Base;

namespace JDI_Core.Interfaces.Complex
{
    public interface IForm<in T> : IComposite, ISetValue, IElement
    {
        
        /**
         * @param entity Specify entity
         *               Fills all elements on the form which implements SetValue interface and can be matched with fields in input entity
         */
        //TODO[JDIAction]
        //TODO Fill() was default
        void Fill(T entity);

        /**
         * @param map Specify entity as map
         *            Fills all elements on the form which implements SetValue interface and can be matched with fields in input entity
         */
        //TODO[JDIAction]
        //TODO Fill() was default
        void Fill(Dictionary<string, string> map);

        
        /**
         * @param entity Specify entity
         *               Verify that form filled correctly. If not returns list of keys where verification fails
         */
        //TODO[JDIAction]
        //TODO Verify() was default
        IList<string> Verify(T entity);

        /**
         * @param map Specify entity as map
         *            Fills all elements on the form which implements SetValue interface and can be matched with fields in input entity
         */
        //TODO[JDIAction]
        //TODO Verify was default
        IList<string> Verify(Dictionary<string, string> map);

       
        /**
         * @param entity Specify entity
         *               Verify that form filled correctly. If not returns list of keys where verification fails
         */
        //TODO[JDIAction]
        //TODO Check() was default
        void Check(T entity);
        /**
         * @param map Specify entity as map
         *            Fills all elements on the form which implements SetValue interface and can be matched with fields in input entity
         */
        //TODO[JDIAction]
        //TODO Check() was default
        void Check(Dictionary<string, string> map);

        /**
         * @param text Specify text
         *             Fill first setable field with value and click on Button “submit” <br>
         * @apiNote To use this option Form pageObject should have at least one ISetValue element and only one IButton WebElement
         */
        //TODO[JDIAction]
        void Submit(string text);

        /**
         * @param text       Specify text
         * @param buttonName button name for form submiting
         *                   Fill first setable field with value and click on Button “buttonName” <br>
         * @apiNote To use this option Form pageObject should have at least one ISetValue element <br>
         * Allowed different buttons to send one form e.g. save/ publish / cancel / search update ...
         */
        //TODO[JDIAction]
        void Submit(string text, string buttonName);


        /**
         * @param text Specify text
         *             Fill first setable field with value and click on Button “login” or ”loginButton” <br>
         * @apiNote To use this option Form pageObject should have at least one ISetValue element <br>
         * Allowed different buttons to send one form e.g. save/ publish / cancel / search update ...
         */
        //TODO[JDIAction]
        //TODO Login was default
        void Login(string text);

        /**
         * @param text Specify text
         *             Fill first setable field with value and click on Button “add” or ”addButton” <br>
         * @apiNote To use this option Form pageObject should have at least one ISetValue element <br>
         * Allowed different buttons to send one form e.g. save/ publish / cancel / search update ...
         */
        //TODO[JDIAction]
        //TODO Add() was default
        void Add(string text);

        /**
         * @param text Specify text
         *             Fill first setable field with value and click on Button “publish” or ”publishButton” <br>
         * @apiNote To use this option Form pageObject should have at least one ISetValue element <br>
         * Allowed different buttons to send one form e.g. save/ publish / cancel / search update ...
         */
        //TODO[JDIAction]
        //TODO Publish() was default
        void Publish(string text);

        /**
         * @param text Specify text
         *             Fill first setable field with value and click on Button “save” or ”saveButton” <br>
         * @apiNote To use this option Form pageObject should have at least one ISetValue element <br>
         * Allowed different buttons to send one form e.g. save/ publish / cancel / search update ...
         */
        //TODO[JDIAction]
        //TODO Save() was default
        void Save(string text);

        /**
         * @param text Specify text
         *             Fill first setable field with value and click on Button “update” or ”updateButton” <br>
         * @apiNote To use this option Form pageObject should have at least one ISetValue element <br>
         * Allowed different buttons to send one form e.g. save/ publish / cancel / search update ...
         */
        //TODO[JDIAction]
        //TODO Update() was default
        void Update(string text);

        /**
         * @param text Specify text
         *             Fill first setable field with value and click on Button “cancel” or ”cancelButton” <br>
         * @apiNote To use this option Form pageObject should have at least one ISetValue element <br>
         * Allowed different buttons to send one form e.g. save/ publish / cancel / search update ...
         */
        //TODO[JDIAction]
        //TODO Cancel() was default
        void Cancel(string text);

        /**
         * @param text Specify text
         *             Fill first setable field with value and click on Button “close” or ”closeButton” <br>
         * @apiNote To use this option Form pageObject should have at least one ISetValue element <br>
         * Allowed different buttons to send one form e.g. save/ publish / cancel / search update ...
         */
        //TODO[JDIAction]
        //TODO  close was default
        void Close(string text);

        /**
         * @param text Specify text
         *             Fill first setable field with value and click on Button “back” or ”backButton” <br>
         * @apiNote To use this option Form pageObject should have at least one ISetValue element <br>
         * Allowed different buttons to send one form e.g. save/ publish / cancel / search update ...
         */
        //TODO[JDIAction]
        //TODO Back was default
        void Back(string text);

        /**
         * @param text Specify text
         *             Fill first setable field with value and click on Button “select” or ”selectButton” <br>
         * @apiNote To use this option Form pageObject should have at least one ISetValue element <br>
         * Allowed different buttons to send one form e.g. save/ publish / cancel / search update ...
         */
        //TODO[JDIAction]
        //TODO select() was default
        void Select(string text);

        /**
         * @param text Specify text
         *             Fill first setable field with value and click on Button “next” or ”nextButton” <br>
         * @apiNote To use this option Form pageObject should have at least one ISetValue element <br>
         * Allowed different buttons to send one form e.g. save/ publish / cancel / search update ...
         */
        //TODO[JDIAction]
        //TODO Next() was default
        void Next(string text);

        /**
         * @param text Specify text
         *             Fill first setable field with value and click on Button “search” or ”searchButton” <br>
         * @apiNote To use this option Form pageObject should have at least one ISetValue element <br>
         * Allowed different buttons to send one form e.g. save/ publish / cancel / search update ...
         */
        //TODO[JDIAction]
        //TODO Search() was default
        void Search(string text);

        /**
         * @param entity Specify entity
         *               Fill all SetValue elements and click on Button “submit” <br>
         * @apiNote To use this option Form pageObject should have only one IButton WebElement
         */
        //TODO[JDIAction]
        //TODO Submit() was default
        void Submit(T entity);

        /**
         * @param entity Specify entity
         *               Fill all SetValue elements and click on Button “login” or ”loginButton” <br>
         * @apiNote To use this option Form pageObject should have only one IButton WebElement
         */
        //TODO[JDIAction]
        //TODO Login() was default
        void Login(T entity);

        /**
         * @param entity Specify entity
         *               Fill all SetValue elements and click on Button “add” or ”addButton” <br>
         * @apiNote To use this option Form pageObject should have only one IButton WebElement
         */
        //TODO[JDIAction]
        //TODO Add() was default
        void Add(T entity);

        /**
         * @param entity Specify entity
         *               Fill all SetValue elements and click on Button “publish” or ”publishButton” <br>
         * @apiNote To use this option Form pageObject should have only one IButton WebElement
         */
        //TODO[JDIAction]
        //TODO Publish() was default
        void Publish(T entity);

        /**
         * @param entity Specify entity
         *               Fill all SetValue elements and click on Button “save” or ”saveButton” <br>
         * @apiNote To use this option Form pageObject should have only one IButton WebElement
         */
        //TODO[JDIAction]
        //TODO Save() was default
        void Save(T entity);

        /**
         * @param entity Specify entity
         *               Fill all SetValue elements and click on Button “update” or ”updateButton” <br>
         * @apiNote To use this option Form pageObject should have only one IButton WebElement
         */
        //TODO[JDIAction]
        //TODO Uodate was default
        void Update(T entity);

        /**
         * @param entity Specify entity
         *               Fill all SetValue elements and click on Button “cancel” or ”cancelButton” <br>
         * @apiNote To use this option Form pageObject should have only one IButton WebElement
         */
        //TODO[JDIAction]
        //TODO Cancel() was default 
        void Cancel(T entity);

        /**
         * @param entity Specify entity
         *               Fill all SetValue elements and click on Button “close” or ”closeButton” <br>
         * @apiNote To use this option Form pageObject should have only one IButton WebElement
         */
        //TODO[JDIAction]
        //TODO Close was default
        void Close(T entity);

        /**
         * @param entity Specify entity
         *               Fill all SetValue elements and click on Button “back” or ”backButton” <br>
         * @apiNote To use this option Form pageObject should have only one IButton WebElement
         */
        //TODO[JDIAction]
        //TODO Back() was default
        void Back(T entity);

        /**
         * @param entity Specify entity
         *               Fill all SetValue elements and click on Button “select” or ”selectButton” <br>
         * @apiNote To use this option Form pageObject should have only one IButton WebElement
         */
        //TODO[JDIAction]
        //TODO was default
        void Select(T entity);

        /**
         * @param entity Specify entity
         *               Fill all SetValue elements and click on Button “next” or ”nextButton” <br>
         * @apiNote To use this option Form pageObject should have only one IButton WebElement
         */
        //TODO[JDIAction]
        //TODO Next() was default
        void Next(T entity);

        /**
         * @param entity Specify entity
         *               Fill all SetValue elements and click on Button “search” or ”searchButton” <br>
         * @apiNote To use this option Form pageObject should have only one IButton WebElement
         */
        //TODO[JDIAction]
        //TODO Search() was default
        void Search(T entity);

        /**
         * @param buttonName Specify Button Name
         * @param entity     Specify entity
         *                   Fill all SetValue elements and click on Button specified button e.g. "Publish" or "Save" <br>
         * @apiNote To use this option Form pageObject should have button names in specific format <br>
         * e.g. if you call "submit(user, "Publish") then you should have WebElement 'publishButton'. <br>
         * * Letters case in button name  no matters
         */
        //TODO[JDIAction]
        void Submit(T entity, string buttonName);

        /**
         * @param buttonName Specify Button Name
         * @param entity     Specify entity
         *                   Fill all SetValue elements and click on Button specified button e.g. "Publish" or "Save" <br>
         * @apiNote To use this option Form pageObject should have button names in specific format <br>
         * e.g. if you call "submit(user, "Publish") then you should have WebElement 'publishButton'. <br>
         * * Letters case in button name  no matters
         */
        //TODO[JDIAction]
        void Submit(T entity, Enum buttonName);

        /**
         * @param objstrings Fill all SetValue elements and click on Button specified button e.g. "Publish" or "Save" <br>
         * @apiNote To use this option Form pageObject should have button names in specific format <br>
         * e.g. if you call "submit(user, "Publish") then you should have WebElement 'publishButton'. <br>
         * * Letters case in button name  no matters
         */
        //TODO[JDIAction]
        void Submit(Dictionary<string, string> objstrings);
    }
}
