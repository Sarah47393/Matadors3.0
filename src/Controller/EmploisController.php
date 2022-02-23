<?php

namespace App\Controller;

use App\Entity\Emplois;
use App\Form\EmploisType;
use App\Repository\EmploisRepository;
use Doctrine\ORM\EntityManagerInterface;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;

/**
 * @Route("/emplois")
 */
class EmploisController extends AbstractController
{
    /**
     * @Route("/", name="emplois_index", methods={"GET"})
     */
    public function index(EmploisRepository $emploisRepository): Response
    {
        return $this->render('emplois/index.html.twig', [
            'emplois' => $emploisRepository->findAll(),
        ]);
    }

    /**
     * @Route("/new", name="emplois_new", methods={"GET", "POST"})
     */
    public function new(Request $request, EntityManagerInterface $entityManager): Response
    {
        $emploi = new Emplois();
        $form = $this->createForm(EmploisType::class, $emploi);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $user = $form->get('User')->getData();
            $emploi->setnom($user->getnom());
            $emploi->setprenom($user->getprenom());
            $emploi->setCIN($user->getcin());
            $entityManager->persist($emploi);
            $entityManager->flush();

            return $this->redirectToRoute('emplois_index', [], Response::HTTP_SEE_OTHER);
        }

        return $this->render('emplois/new.html.twig', [
            'emploi' => $emploi,
            'form' => $form->createView(),
        ]);
    }

    /**
     * @Route("/{id}", name="emplois_show", methods={"GET"})
     */
    public function show(Emplois $emploi): Response
    {
        return $this->render('emplois/show.html.twig', [
            'emploi' => $emploi,
        ]);
    }

    /**
     * @Route("/{id}/edit", name="emplois_edit", methods={"GET", "POST"})
     */
    public function edit(Request $request, Emplois $emploi, EntityManagerInterface $entityManager): Response
    {
        $form = $this->createForm(EmploisType::class, $emploi);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $user = $form->get('User')->getData();
            $emploi->setnom($user->getnom());
            $emploi->setprenom($user->getprenom());
            $emploi->setCIN($user->getcin());
            $entityManager->flush();

            return $this->redirectToRoute('emplois_index', [], Response::HTTP_SEE_OTHER);
        }

        return $this->render('emplois/edit.html.twig', [
            'emploi' => $emploi,
            'form' => $form->createView(),
        ]);
    }

    /**
     * @Route("/{id}", name="emplois_delete", methods={"POST"})
     */
    public function delete(Request $request, Emplois $emploi, EntityManagerInterface $entityManager): Response
    {
        if ($this->isCsrfTokenValid('delete'.$emploi->getId(), $request->request->get('_token'))) {
            $entityManager->remove($emploi);
            $entityManager->flush();
        }

        return $this->redirectToRoute('emplois_index', [], Response::HTTP_SEE_OTHER);
    }
}
